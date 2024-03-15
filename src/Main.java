import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //Task1();
        //Task2();
        //Task3();
        //Task4();
        //Task5();
        //Task6();
        //Task7();
        Task8();
    }

    public static void Task1() {
        /*Отсортировать строки файла, содержащие названия книг, в алфавитном
         порядке с использованием двух деков.*/
        File file = new File("txtInputTask1.txt");
        Deque<String> deque = new ArrayDeque<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                deque.add(data);
            }
            scanner.close();
            String[] arr = deque.toArray(new String[0]);
            for (int i = 0; i < arr.length - 1; i++) {
                int indexMin = i;
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j].compareTo(arr[indexMin]) < 0) {
                        indexMin = j;
                    }
                }
                String temp = arr[indexMin];
                arr[indexMin] = arr[i];
                arr[i] = temp;
            }
            Deque<String> sortedDeque = new ArrayDeque<>(Arrays.asList(arr));
            File file1 = new File("txtOutputTask1.txt");
            FileWriter fileWriter = new FileWriter(file1);
            for (String st : sortedDeque) {
                fileWriter.write(st);
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Task2() {
       /* Дек содержит последовательность символов для шифровки сообщений.
        Дан текстовый файл, содержащий зашифрованное сообщение. Пользуясь деком, расшифровать текст.
        Известно, что при шифровке каждый символ сообщения заменялся следующим за ним в деке по часовой стрелке через один.*/
        //Для расшифровки зашифрованного сообщения из текстового файла с использованием дека можно воспользоваться следующим примером на языке Java:
        // Создание дека для шифрования и расшифрования
        Deque<Character> deque = new ArrayDeque<>();
        String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"; // алфавит для заполнения дека
        //рйгёу нйс
        for (char c : alphabet.toCharArray()) {
            deque.add(c);
        }

        // Чтение зашифрованного сообщения из файла
        try {
            File file = new File("inputTask2.txt");
            Scanner scanner = new Scanner(file);
            String encryptedMessage = scanner.nextLine();
            scanner.close();

            // Расшифровка сообщения
            StringBuilder decryptedMessage = new StringBuilder();
            for (char c : encryptedMessage.toCharArray()) {
                if (Character.isLetter(c)) {
                    char decryptedChar = decryptChar(deque, c);
                    decryptedMessage.append(decryptedChar);
                } else {
                    decryptedMessage.append(c);
                }
            }

            // Запись расшифрованного сообщения в файл
            FileWriter writer = new FileWriter("outputTask2.txt");
            writer.write(decryptedMessage.toString());
            writer.close();
            System.out.println("Расшифрованное сообщение сохранено в файл outputTask2.txt");
        } catch (IOException e) {
            System.out.println("Произошла ошибка при чтении или записи файла.");
            e.printStackTrace();
        }
    }

    // Метод для расшифровки символа с использованием дека
    private static char decryptChar(Deque<Character> deque, char encryptedChar) {
        char decryptedChar = ' ';
        for (int i = 0; i < 33; i++) {
            char currentChar = deque.removeLast();
            if (currentChar == encryptedChar) {
                decryptedChar = deque.peekLast();
            }
            deque.addFirst(currentChar);
        }
        return decryptedChar;
    }

    public static void Task3() {
        // Создание трех стеков для представления стержней A, B, C
        Stack<Integer> stackA = new Stack<>();
        Stack<Integer> stackB = new Stack<>();
        Stack<Integer> stackC = new Stack<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Здесь следует добавить код для чтения информации о дисках из исходного файла и их размещения на стержне A
        int i = 0;
        while (n != 0) {
            stackA.add(i);
            i++;
            n--;
        }
        // Вызов метода для переноса дисков со стержня A на стержень C, используя стержень B в качестве промежуточного
        moveDisks(stackA.size(), stackA, stackC, stackB);
        System.out.println(stackC.size());
        System.out.println(stackB.size());
        System.out.println(stackA.size());
    }

    // Метод для рекурсивного переноса дисков
    static void moveDisks(int n, Stack<Integer> source, Stack<Integer> destination, Stack<Integer> auxiliary) {
        if (n > 0) {
            moveDisks(n - 1, source, auxiliary, destination);
            destination.push(source.pop());
            moveDisks(n - 1, auxiliary, destination, source);
        }
    }
    public static void Task4() {
        /*Дан текстовый файл с программой на алгоритмическом языке.
        За один просмотр файла проверить баланс круглых скобок в тексте, используя стек*/
        String fileName = "inputTask4.txt"; // Замените на имя вашего файла
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int i=0;
            while ((line = br.readLine()) != null) {
                i++;
                if (checkBalancedParentheses(line)) {
                    System.out.println(i+" = Скобки в строке сбалансированы");
                } else {
                    System.out.println(i+" = Скобки в строке не сбалансированы");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static boolean checkBalancedParentheses(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
    public static void Task5(){
        /*Дан текстовый файл с программой на алгоритмическом языке.
        За один просмотр файла проверить баланс квадратных скобок в тексте, используя дек.*/
        String fileName = "inputTask4.txt"; // Замените на имя вашего файла
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int i=0;
            while ((line = br.readLine()) != null) {
                i++;
                if (checkBalancedSquareBrackets(line)) {
                    System.out.println(i+" = Квадратные скобки в строке сбалансированы");
                } else {
                    System.out.println(i+" = Квадратные скобки в строке не сбалансированы");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static boolean checkBalancedSquareBrackets(String str) {
        Deque<Character> stack = new LinkedList<>();
        for (char c : str.toCharArray()) {
            if (c == '[') {
                stack.push(c);
            } else if (c == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
    public static void Task6(){
        /*Дан файл из символов. Используя стек, за один просмотр файла напечатать сначала все цифры,
        затем все буквы, и, наконец, все остальные символы, сохраняя исходный порядок в каждой группе символов.*/
        String fileName = "inputTask6.txt"; // Замените на имя вашего файла
        Stack<Character> stackDigit = new Stack<>();
        Stack<Character> stackLetter = new Stack<>();
        Stack<Character> others = new Stack<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            //numbers 48-57
            //letterEn 65-90 97-122
            //letterRu 1040-1071 1072-1105
            while ((line = br.readLine()) != null) {
                for(int i=0; i<line.length(); i++){
                    int c = line.charAt(i);
                    if(line.charAt(i)!=' ') {
                        if (c >= 48 && c <= 57) {
                            stackDigit.push(line.charAt(i));
                        } else if (c >= 65 && c <= 90 || c >= 97 && c <= 122 || c >= 1040 && c <= 1071 || c >= 1072 && c <= 1105) {
                            stackLetter.push(line.charAt(i));
                        } else {
                            others.push(line.charAt(i));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Character c:stackDigit){
            System.out.print(c);
        }
        System.out.println();
        for(Character c:stackLetter){
            System.out.print(c);
        }
        System.out.println();
        for(Character c:others){
            System.out.print(c);
        }
    }
    public static void Task7(){
        /*Дан файл из целых чисел. Используя дек,
        за один просмотр файла напечатать сначала все отрицательные числа, затем все положительные числа,
        сохраняя исходный порядок в каждой группе.*/
        Deque<Integer> deque = new ArrayDeque<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputTask7.txt"))) {
            String line;
            while ((line=br.readLine())!=null){
                String[] str = line.split("\\s++");
                for(int i=0; i<str.length; i++){
                    deque.push(Integer.parseInt(String.valueOf(str[i])));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(Integer i: deque){
            if(i<0){
                System.out.print(i+" ");
            }
        }
        System.out.println();
        for(Integer i: deque){
            if(i>0){
                System.out.print(i+" ");
            }
        }
    }
    public static void Task8() throws IOException {
        /*Дан текстовый файл. Используя стек, сформировать новый текстовый файл, содержащий строки исходного файла,
        записанные в обратном порядке: первая строка становится последней, вторая – предпоследней и т.д.*/
        Stack<String> stack = new Stack<>();;
        try (BufferedReader br = new BufferedReader(new FileReader("inputTask8.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                stack.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File file = new File("outputTask8.txt");
        FileWriter fileWriter = new FileWriter(file);
        while (!stack.isEmpty()){
            fileWriter.write(stack.pop());
            fileWriter.write("\n");
        }
        fileWriter.close();
    }
}