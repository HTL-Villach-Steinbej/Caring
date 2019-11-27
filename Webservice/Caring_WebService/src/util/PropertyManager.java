package util;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
	
	private static PropertyManager instance = null;
	private String configFile = "properties.config";
	private Properties props = new Properties(); 
	
	private PropertyManager(){
	}
	public static PropertyManager getInstance() throws FileNotFoundException, IOException{
		if( PropertyManager.instance == null){
			instance = new PropertyManager();
			instance.fillProperties();			
		}
		return instance; 
	}
	
	private void fillProperties() throws FileNotFoundException, IOException{
		try(FileReader reader = new FileReader(this.configFile)){
			this.props.load( reader );
		}
	}
	public String readProperty( String name){
		return this.props.getProperty(name); 
		
	}
	

}
