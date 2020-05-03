public class LabResearch {

   String race;
   int acquiredPoints = 0;

   // This is the constructor of the class LabResearch
   public LabResearch(String race) {
      this.race = race;
   }

   // Assign the number of points acquired of the race to the variable acquiredPoints.
   public void setAcquiredPoints(int acquiredPoints) {
	   this.acquiredPoints = acquiredPoints;
   }
   
   /* Print the LabResearch details */
   public void printLabReserachSoFar() {
      System.out.println("race: "+ race);
      System.out.println("acquiredPoints: " + acquiredPoints);
   }
   
   
}