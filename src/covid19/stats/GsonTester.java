package covid19.stats;

import com.google.gson.Gson; 
import com.google.gson.GsonBuilder;  


public class GsonTester { 
   public static void main() { 
      String jsonString = "{\"name\":\"Mahesh\", \"age\":21}"; 
      
      GsonBuilder builder = new GsonBuilder(); 
      builder.setPrettyPrinting(); 
      
      Gson gson = builder.create(); 
      Student student = gson.fromJson(jsonString, Student.class); 
      System.out.println(student);    
      
      jsonString = gson.toJson(student); 
      System.out.println(jsonString);  
   } 
} 
