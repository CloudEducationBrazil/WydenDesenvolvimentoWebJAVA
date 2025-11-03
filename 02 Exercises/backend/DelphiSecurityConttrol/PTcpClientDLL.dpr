library PTcpClientDLL;

uses
  Windows, SysUtils, Classes, IdTCPClient, Dialogs;

{$H-} // Importante: não usar long strings (compatível com Delphi 3)

// Aloca uma cópia de um texto como PChar
function AllocPChar(const Texto: string): PChar;
begin
  Result := StrAlloc(Length(Texto) + 1);
  StrPCopy(Result, Texto);
end;

// Função principal: Envia comando TCP e retorna resposta como PChar
procedure EnviarComandoTCP(
  Host: PChar;
  Port: Integer;
  const Comando: PChar;
  var Resposta: PChar;
  var Erro: PChar
); stdcall;
var
  Cliente: TIdTCPClient;
  Linha: string;
  Resultado: string;
begin
  Resposta := nil;
  Erro  := nil;

  Cliente := TIdTCPClient.Create(nil);
  try
    Cliente.Host := Host;//'192.168.0.48';
    Cliente.Port := Port;
    Cliente.ReadTimeout := 3000;  // 3 segundos
    //Cliente.Host := '127.0.0.1';
    //Cliente.Port := 8587;

    try
      Cliente.Connect;
      Cliente.WriteLn(Comando); //comando'GET_CONTABIL'

      Resultado := '';
      if Cliente.Connected then
      begin
        //ShowMessage('conectado ');
        repeat
          Linha := Cliente.ReadLn;
          if Linha = 'END' then Break;

          Resultado := Resultado + Linha + #13#10;
        until False;

        Cliente.Disconnect;

        // Retorna resultado como PChar
        if ( System.Pos('TOKEN', Resultado) = 0 ) then // Não Encontrou
          Resposta := AllocPChar(Resultado)
        else
          Resposta := AllocPChar('');

        //ShowMessage('Resposta: ' + Resposta);
      end;

    except
      on E: Exception do
      begin
        Erro := AllocPChar(E.ClassName + ': ' + E.Message);
        ShowMessage('Erro: ' + Erro);
     end;
    end;

  finally
    Cliente.Free;
  end;
end;

// Libera memória de um PChar alocado pela DLL
procedure LiberarPChar(P: PChar); stdcall;
begin
  if P <> nil then
    StrDispose(P);
end;

exports
  EnviarComandoTCP,
  LiberarPChar;

begin
end.
