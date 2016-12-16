package com.json;

import java.util.ArrayList;

public interface IVoyage {

	String whoTheMost(ArrayList<Deputy> deputies);
	
	String whoTheLong(ArrayList<Deputy> deputies);
	
	String whichWasTheMostExpensive(ArrayList<Deputy> deputies);
	
	ArrayList<String> whoVisitItaly(ArrayList<Deputy> deputies);	
}
