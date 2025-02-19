package Strategy;
import java.time.LocalDateTime;

public class WeekdayAccessStrategy implements AccessStrategy {
    @Override
    public boolean canAccess() {
        LocalDateTime now = LocalDateTime.now();
        return now.getDayOfWeek().getValue() <= 5; // Monday to Friday
    }
}

