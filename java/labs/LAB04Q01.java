import java.util.Scanner;

public class LAB04Q01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        while (!sair && scanner.hasNext()) {

            int num_reps = scanner.nextInt();
            int modulo = scanner.nextInt();

            if ((modulo == 0) && (num_reps == 0)){
                sair = true;
                System.out.println(num_reps + " " + modulo);
            }


            if (!sair) {
                int[] array = new int[num_reps];
                for(int i = 0; i < num_reps; i++)
                    array[i] = scanner.nextInt();
                
                Sort_by_modulo(array, modulo);

                System.out.println(num_reps + " " + modulo);
                for(int x : array){
                    System.out.println(x);
                }

            }
        }
        scanner.close();
    }

    public static int[] Calcula_array_modulos(int[] array, int modulo) {

        int[] modulos = new int[array.length];

        int i = 0;

        for (int x : array) {
            modulos[i] = x % modulo;
            i++;
        }

        return modulos;
    }

    public static void Sort_by_modulo(int[] array, int module) {

        int[] modulos = Calcula_array_modulos(array, module);

        for (int i = 1; i < array.length; i++) {
            int tmp = modulos[i];
            int aux = array[i];
            int j = i - 1;
            boolean sair = false;

            while ((j >= 0) && (modulos[j] > tmp) && (!sair)) {
                if(modulos[j] == tmp){
                    boolean j_par = (array[j] % 2 == 0) ? true : false;
                    boolean tmp_par = (aux % 2 == 0) ? true : false;

                    if(j_par && !tmp_par){
                        array[j + 1] = array[j];
                        modulos[j +1] = modulos[j];
                        j--;
                    }
                    else if(!j_par && tmp_par){
                        sair = true;
                    }
                    else if(!j_par && !tmp_par){
                        if(array[j] <= aux){
                            array[j + 1] = array[j];
                            modulos[j + 1] = modulos[j];
                            j--;
                        }
                        else sair = true;
                    }
                    else if(j_par && tmp_par){
                        if(array[j] >= aux){
                            array[j + 1] = array[j];
                            modulos[j +1] = modulos[j];
                            j--;
                        }
                        else sair = true;
                    }

                }
                else{
                    array[j + 1] = array[j];
                    modulos[j +1] = modulos[j];
                    j--;
                }
            }
            array[j + 1] = aux;
            modulos[j + 1] = tmp;
        }

    }

}