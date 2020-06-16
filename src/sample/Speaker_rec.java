import com.bitsinharmony.recognito.*;

import java.util.*;
import java.io.*;


public class Speaker_rec {

	@SuppressWarnings("unused")
	boolean recognito(String s, String audio) throws Exception {



// Create a new Recognito instance defining the audio sample rate to be used

Recognito<String> recognito = new Recognito<String>(16000.0f);

VoicePrint print = recognito.createVoicePrint("Apurva", new File("C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\src\\speech\\dataset\\APURVA1.wav"));
print = recognito.createVoicePrint("Antriksh", new File("C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\src\\speech\\dataset\\ANTRIKSH.wav"));
print = recognito.createVoicePrint("Esha", new File("C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\src\\speech\\dataset\\Esha Manwani.wav"));
print = recognito.createVoicePrint("Aditya", new File("C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\src\\speech\\dataset\\OJHA.wav"));
print = recognito.createVoicePrint("Anubhuti", new File("C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\src\\speech\\dataset\\ANUBHUTI1.wav"));
print = recognito.createVoicePrint("Tejas", new File("C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\src\\speech\\dataset\\TEJAS.wav"));
print = recognito.createVoicePrint("Joseph", new File("C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\src\\speech\\dataset\\Joseph Jose.wav"));
print = recognito.createVoicePrint("Iyer", new File("C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\src\\speech\\dataset\\Aditha Iyer.wav"));
print = recognito.createVoicePrint("Malvika", new File("C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\src\\speech\\dataset\\Malvika Vardhan.wav"));
print = recognito.createVoicePrint("Mithali", new File("C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\src\\speech\\dataset\\Mithali Palkar.wav"));
print = recognito.createVoicePrint("Nivedha", new File("C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\src\\speech\\dataset\\Nivedha.wav"));
print = recognito.createVoicePrint("Onkar", new File("C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\src\\speech\\dataset\\Onkar Kishan Khullar.wav"));
print = recognito.createVoicePrint("Ravi", new File("C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\src\\speech\\dataset\\Ravi Dubey.wav"));
print = recognito.createVoicePrint("Saurabhi", new File("C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\src\\speech\\dataset\\Saurabhi Gautam.wav"));
print = recognito.createVoicePrint("Sreenath", new File("C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\src\\speech\\dataset\\Sreenath Bhasi.wav"));


// Compares this audio file with every single one in the database and stores the results in "matches" like a list
List<MatchResult<String>> matches = recognito.identify(new File(audio));

//The first result of matches is obtained by get which is supposed to be the likeliest
//and stores it in match
MatchResult<String> match = matches.get(0);

//Storing name and likelihood ratio separately here
String name = match.getKey();
int likelihood_ratio = match.getLikelihoodRatio();
System.out.println(likelihood_ratio);
System.out.println(name);

//Return the name if true else not 
if(likelihood_ratio>=65 && name.equals(s) )
{

	return true;
    //System.out.println("This is Apurva !!! " + likelihood_ratio + "% positive about it...");

}

else {

	return false;
//System.out.println("No match!");

}
}
}