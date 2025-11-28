package Thread;

class MinhaThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + " executando: " + i);
            try {
                Thread.sleep(500); // pausa de 0.5 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}