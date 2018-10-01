package com.aliimran.game2048;

public enum Numbers {
	NUMBER2,
	NUMBER4,
	NUMBER8,
	NUMBER16,
	NUMBER32,
	NUMBER64,
	NUMBER128,
	NUMBER256,
	NUMBER512,
	NUMBER1024,
	NUMBER2048;
	
	public String toString(){
		if(this.ordinal() == 0){
			return "2";
		}
		else if(this.ordinal() == 1){
			return "4";
		}
		else if(this.ordinal() == 2){
			return "8";			
		}
		else if(this.ordinal() == 3){
			return "16";
		}
		else if(this.ordinal() == 4){
			return "32";
		}
		else if(this.ordinal() == 5){
			return "64";
		}
		else if(this.ordinal() == 6){
			return "128";
		}
		else if(this.ordinal() == 7){
			return "256";
		}
		else if(this.ordinal() == 8){
			return "512";
		}
		else if(this.ordinal() == 9){
			return "1024";
		}
		else if(this.ordinal() == 10){
			return "2048";
		}
		return null;
		
	}
}
