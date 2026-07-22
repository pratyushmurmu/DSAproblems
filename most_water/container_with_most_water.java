public class container_with_most_water{
 public int maxArea(int[] height){
    int maxArea=0;
    // Brute force approach
    for(int i=0; i<height.length;i++){
        for(int j=i+1; j<height.length;j++){
            int length= j-i;
            int breadth= Math.min(height[i], height[j]);
            //int maxArea= Math.max(maxArea, length*breadth);
            maxArea= Math.max(maxArea, length*breadth);
        }
    }
    return maxArea;
 }
 public static void main(String[] args){
    int [] height ={1,8,6,2,5,4,8,3,7};
    container_with_most_water area= new container_with_most_water();
    System.out.println(area.maxArea(height));
 }
}
