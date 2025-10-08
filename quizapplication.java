import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.classfile.BufWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class quizapplication {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        List<Question> questions=new ArrayList<>();
        try(BufferedReader br=new BufferedReader(new FileReader("questions.txt"))){
            String line;
            while((line=br.readLine())!=null){
                String questiontext=line;
                String[] option=new String[4];
                for(int i=0;i<4;i++){
                    option[i]=br.readLine();
                }
                int correctans=Integer.parseInt(br.readLine());
                questions.add(new Question(questiontext, option, correctans));
                br.readLine();
            }
        }catch(IOException e){
            System.out.println("Error while reading questions ");
            return; 
        }

        System.out.println("Enter your name ");
        String playername=sc.nextLine();
        int score=0;
        //ask question 
        // print question
        for(int i=0;i<questions.size();i++){
            Question q=questions.get(i);
            System.out.println("Q"+(i+1)+"."+q.QuestionText);
            // print option 
            for(int j=0;j<4;j++){
                System.out.println(j+1+". "+q.options[j]);
            }
            System.out.print("Enter your answer(1-4) : " );
            int answer=sc.nextInt();
            if(answer==q.correctans){
                System.out.println("correct answer ");
                score++;
            }
            else{
                System.out.println("Incorrect answer ");
            }
        }
        
        File scorefile=new File("Scorefile.txt");
        try(BufferedWriter br=new BufferedWriter(new FileWriter("Score.txt",true))){
            br.write(playername+"-"+"Score - " +score+"/"+questions.size());
            br.newLine();//   (\n) for next line 
            
        }catch(IOException e){
            System.out.println("Error Ocurred in score file ");
        }
        sc.close();
    }
    
}
class Question{
    String QuestionText;
    String[] options;
    int correctans;

    Question(String questiontext,String[] option,int correct){
        this.QuestionText=questiontext;
        this.options=option;
        this.correctans=correct;
    }
}
