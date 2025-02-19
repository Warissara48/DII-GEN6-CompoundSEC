package Strategy;

public class EverydayAccessStrategy implements AccessStrategy {
    @Override
    public boolean canAccess() {
        return true;  // VIP has unrestricted access
    }
}
