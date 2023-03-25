package oldCourse;

public class Util {
    public int getElem(int indx, int[] arr){
        int result = 0;
        if(indx == arr.length-1) {
            return arr[indx];
        }
        result += arr[indx] + getElem(indx+1, arr);
        return result;
    }
}
