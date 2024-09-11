package system.user;

public class UserPlan {
	//Enum to define different types of user plans
    public enum planType {
        trial, standard, vip
    }

    private planType type;
    private boolean isActive;
 // Constructor
    public UserPlan(planType type, boolean isActive) {
        this.type = type;
        this.isActive = isActive;
    }

    public planType getType() {
        return type;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isVip() {
        return type == planType.vip;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "planType=" + type +
                ", isActive=" + isActive +
                '}';
    }
 // Static method to create a UserPlan object from parameters
    public static UserPlan createPlan(String... params) {
        if (params.length != 2) {
            return null;
        }

        planType type;
        try {
            type = planType.valueOf(params[0].toLowerCase());
        } catch (IllegalArgumentException e) {
            return null; 
        }

        boolean isActive;
        try {
            isActive = Boolean.parseBoolean(params[1]);
        } catch (Exception e) {
            return null; 
        }

        return new UserPlan(type, isActive);
    }
}
