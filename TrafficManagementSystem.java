import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

class TrafficSignal {
    private String currentSignal;
    private int greenDuration; 
    private int redDuration;   
    
    public TrafficSignal() {
        currentSignal = "RED";
        greenDuration = 30;  
        redDuration = 30;    
    }

    public void switchSignal() {
        if (currentSignal.equals("RED")) {
            currentSignal = "GREEN";
            System.out.println("Switching signal to GREEN");
            manageSignalDuration();
        } else {
            currentSignal = "RED";
            System.out.println("Switching signal to RED");
            manageSignalDuration();
        }
    }

    private void manageSignalDuration() {
        
        if (currentSignal.equals("GREEN")) {
            greenDuration = new Random().nextInt(10) + 30; 
            System.out.println("Green light duration: " + greenDuration + " seconds");
        } else {
            redDuration = new Random().nextInt(10) + 30; 
            System.out.println("Red light duration: " + redDuration + " seconds");
        }
    }

    public String getCurrentSignal() {
        return currentSignal;
    }

    public int getGreenDuration() {
        return greenDuration;
    }

    public int getRedDuration() {
        return redDuration;
    }
}

class TrafficFlowPredictor {
    
    public int predictTrafficFlow() {
        Random rand = new Random();
        return rand.nextInt(100); 
    }
}

class TrafficManager {
    private TrafficSignal signal;
    private TrafficFlowPredictor predictor;
    private Timer timer;

    public TrafficManager() {
        signal = new TrafficSignal();
        predictor = new TrafficFlowPredictor();
        timer = new Timer();
        scheduleTrafficManagement();
    }

    private void scheduleTrafficManagement() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int trafficFlow = predictor.predictTrafficFlow();
                System.out.println("Current traffic flow: " + trafficFlow);
                adjustTrafficSignal(trafficFlow);
            }
        }, 0, 10000); 
    }

    private void adjustTrafficSignal(int trafficFlow) {
        
        if (trafficFlow > 70) {
            signal.switchSignal();
            System.out.println("High traffic detected. Adjusting signal.");
        } else if (trafficFlow < 30) {
            signal.switchSignal();
            System.out.println("Low traffic detected. Adjusting signal.");
        }
    }
}

public class TrafficManagementSystem {
    public static void main(String[] args) {
        TrafficManager manager = new TrafficManager();
    }
}
