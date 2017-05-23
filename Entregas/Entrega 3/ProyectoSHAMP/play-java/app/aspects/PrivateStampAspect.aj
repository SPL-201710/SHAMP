package aspects;

privileged public aspect PrivateStampAspect 
{
	before():
	    execution(* controllers.IndexController.index(..))
	  {
	    System.out.println("Printing sample:");
	  }
}
