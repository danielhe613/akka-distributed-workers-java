package worker;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class WorkExecutor extends UntypedActor {

  private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

  @Override
  public void onReceive(Object message) {
    if (message instanceof Integer) {
      Integer n = (Integer) message;
      int n2 = n.intValue() * n.intValue();
      String result = n + " * " + n + " = " + n2;
      log.info("Produced result {}", result);
      
      try {
		Thread.sleep(7000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
      getSender().tell(new Worker.WorkComplete(result), getSelf());
    }
  }
}
