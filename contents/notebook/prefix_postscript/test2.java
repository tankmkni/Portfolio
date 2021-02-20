public class Test2{
	public static void main(String[] args){
		
		// prefix
		for( int i = 0; ++i < 2; ){
			System.out.println( "i = " + i );
		}
		
		// postscript
		for( int j = 0; j++ < 2; ){
			System.out.println( "j = " + j );
		}
	}
}