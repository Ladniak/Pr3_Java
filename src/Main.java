public class Main {
    public static void main(String[] args) {
        System.out.println("Оберіть варіант роботи:");
        System.out.println("1. Пошук мінімального елемента у двовимірному масиві.");
        System.out.println("2. Аналіз текстових файлів у директорії.");

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                task1.Task1Main.runTask();
                break;
            case 2:
                task2.Task2Main.runTask();
                break;
            default:
                System.out.println("Невірний вибір. Завершення програми.");
        }
    }
}
