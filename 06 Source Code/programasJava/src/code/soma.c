#include <stdio.h> // Biblioteca do C

int main()
{
  float n1, n2, med;

  printf("Digite num 1:"); scanf("%f", &n1);
  printf("Digite num 2:"); scanf("%f", &n2);
  
  med = n1 + n2 / 2;

  printf("Média: %f", med);
  
  return 0;
}
