public class AudioFile {
	String pathname;
	String filename;
	String author;
	String title;
	
	AudioFile(){}
	
	AudioFile(String path){
	    parsePathname(path);
	    parseFilename(this.filename);
	}
	
	private boolean isWindows() {
		 return System.getProperty("os.name").toLowerCase().indexOf("win") >= 0;
	}
	
	public void parsePathname(String path) {
		String separator;
		// Wenn path nicht leer ist
		if(!path.isEmpty()) {
			// Windows path
			if(isWindows()) {
				separator = "\\";
				// Wenn path wie in Linux ist, ändern wir es wie in Windows
				if(path.charAt(0) == '/' && path.charAt(2) == '/' && path.toLowerCase().charAt(1) >= 'a' && path.toLowerCase().charAt(1) <= 'z') {
					path = path.replace(path.charAt(0), path.charAt(1)).replace(path.charAt(1), ':');
				}
				// Verwandeln wir alle Slashes in Backslashes und machen wir alle Backslashes einsam
				path = path.replaceAll("/+", "/").replaceAll("\\\\+", "\\\\").replace("/", separator);
			}
			// Linux path
			else {
				separator = "/";
				if(path.length() > 1) {
					// Wenn path wie in Windows ist, ändern wir es wie in Linux
					if(path.charAt(1) == ':' && path.toLowerCase().charAt(0) >= 'a' && path.toLowerCase().charAt(0) <= 'z') {
						path = path.replace(path.charAt(0), '/').replace(path.charAt(1), path.charAt(0));
					}
				}
				// Verwandeln wir alle Backslashes in Slashes und machen wir alle Slashes einsam
				path = path.replaceAll("\\\\+", "\\\\").replaceAll("//+", "/").replace("\\", separator);					 
			}
			
			// Überpüfen wir ob es eim Separator gibt, löschen wir einfach die Leerzeichnen der Ende und nehmen Filename
			if (!path.contains(separator)) {
	            this.pathname = path.trim();
	            this.filename = path.trim();
			} else {
	            this.pathname = path.trim();
	            this.filename = path.substring(path.lastIndexOf(separator) + 1).trim();
	        }
		} 
		//Wenn "path" leer ist
		else{
            this.pathname = "";
            this.filename = "";
        } 
	} 
	
	public void parseFilename(String filename) {
		// Wenn filename nicht leer ist
		if(!filename.isEmpty()) {
			// Überprüfen ob filename " - " und "." enthält
			if(filename.trim().contains(" - ") && filename.trim().contains(".")) {
	        	this.author = filename.substring(0, filename.indexOf(" - ")).trim();
	        	this.title = filename.substring(filename.indexOf(" - ") + 3, filename.lastIndexOf('.')).trim();
			} 
			// Wenn filename kein " - " enthält aber "." enthält, ist der Autor leer und der Titel ist der Dateiname bis zum letzten "."
			else if(!filename.contains(" - ") && filename.contains(".")){
				this.author = "";
				this.title = filename.substring(0, filename.lastIndexOf('.'));
			} 
			// Wenn filename weder " - " noch einen Punkt enthält, ist der Autor leer und der Titel ist der Dateiname
			else if(!filename.contains(" - ") && !filename.contains(".")){
				this.author = "";
				this.title = filename.trim();
			} 
			// Wenn filename keine der obigen Bedingungen erfüllt, sind sowohl Autor als auch Titel leer
			else {
				this.author = "";
				this.title = "";
			}
		} 
		else {
			this.author = "";
			this.title = "";
		}
	}

	
	// Getter für Pathname
	public String getPathname() {
		return this.pathname;
	}
	
	// Getter für Filename
	public String getFilename() {
		return this.filename;
	}
	
	// Getter für Author
	public String getAuthor() {
		return this.author;
	}
	
	// Getter für Title
	public String getTitle() {
		return this.title;
	}
	
	public String toString() {
		if(getAuthor() == "") {
			return this.title;
		} else {
			String mediaName = getAuthor() + " - " + getTitle();
			return mediaName;
		}
	}
	
	public static void main(String[] args) {

	}

}
