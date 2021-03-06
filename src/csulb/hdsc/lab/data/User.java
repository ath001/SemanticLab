package csulb.hdsc.lab.data;

import java.util.ArrayList;
import java.util.HashMap;

import csulb.hdsc.lab.ETParser.ColumnMap;
import csulb.hdsc.lab.ETParser.ETParser;
import csulb.hdsc.lab.implementation.Implementation;
import csulb.hdsc.lab.implementation.PupilAvg;

public class User {
	
	private int base_valid_recording;
	private int base_invalid_recording;
	

	private Pupils pupils;
	private Fixations fixations;
	private Saccades saccades;
	private ETParser parser;
	private HashMap<String,Integer> map;
	
	public User( ETParser parser ){
		
		pupils = new Pupils();
		fixations = new Fixations();
		saccades = new Saccades();
		this.parser = parser;
		map = this.parser.getMap();
		
		pupils.setAvgLeftPupil( getPupilAvg("PupilLeft", new PupilAvg() ) );
		pupils.setAvgRightPupil( getPupilAvg("PupilRight", new PupilAvg() ) );
		
		try {
			pupils.setAvgBothPupil();
			System.out.println( pupils.getAvgBothPupil() );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public int getBaseValidNRecords() {
		return base_valid_recording;
	}
	
	public int getBaseInvalidNRecordS(){
		return base_invalid_recording;
	}
	
	
	public double getFixationToSaccadeRatio() {
		return fixations.getSumDuration() / saccades.getSumDuration();
	}
	
	public Pupils getPupilData(){
		return pupils;
		
	}
	
	public Saccades getSaccadesData(){
		return saccades;
	}
	
	public Fixations getFixationsData(){
		return fixations;
	}
	
	private double getPupilAvg(String column_name, Implementation implementation ) {
//		System.out.println( column_name );
//		System.out.println( map.get( column_name ) ); 
		ArrayList<String> pupil_data = parser.getRawColumnData( map.get( column_name ) ); 
		
//		for ( int i = 0 ; i< pupil_data.size(); i++ ) {
//			System.out.println( pupil_data.get(i) );
//		}
		return implementation.compute( pupil_data );
		
	}
	
	
	
	public static void main( String args[] ){
		
		//ColumnMap custom_map = new ColumnMap("./Mappings/QualitiesToColumns.txt");
		ETParser parser = new ETParser("./TestData/ArunData.tsv");
		User user = new User( parser );
		
//		parser.printColumn( parser.getMap().get("SaccadeIndex") );
		parser.printColumn( parser.getMap().get("PupilLeft") );
//		parser.printColumn( parser.getMap().get("AbsoluteSaccadicDirection") );
		
		HashMap<String,Integer> map = parser.getMap();
		ArrayList<String> indexData = parser.getRawColumnData( map.get("SaccadeIndex"));
		ArrayList<String> AbsSaccadeData = parser.getRawColumnData( map.get("AbsoluteSaccadicDirection"));
		System.out.println( indexData.size() );
		System.out.println( AbsSaccadeData.size() );

		for( int i = 0 ; i < indexData.size() ; i++ ) {
			System.out.println( indexData.get(i) + "  " + AbsSaccadeData.get(i) );
		}
		
		
		
	}
}
