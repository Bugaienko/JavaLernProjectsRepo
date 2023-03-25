package sortingAlgorithms;

public class MainHomework04 {
    public static void main(String[] args) {
        int lng = 20;
        Element[] array = Util.initArray(lng);
        Util.printArray(array);

        float startTime = System.currentTimeMillis();
        Result result = new Result(array);

        System.out.println("Finish");
        System.out.print("type1: ");
        Util.printArray(result.getListType1());
        System.out.print("type2: ");
        Util.printArray(result.getListType2());
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
