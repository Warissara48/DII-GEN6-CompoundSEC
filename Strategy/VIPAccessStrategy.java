package Strategy;

public class VIPAccessStrategy implements AccessStrategy {
    @Override
    public boolean canAccess() {
        return true; // VIP has unrestricted access
    }
}
