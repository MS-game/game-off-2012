package msgame;

public class AABB {
    public double minX, minY, maxX, maxY;

    public AABB(double minX, double minY, double maxX, double maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void move(double xadd, double yadd) {
        minX += xadd;
        maxX += xadd;
        minY += yadd;
        maxY += yadd;
    }

    public AABB expand(double x, double y) {
        double minX = this.minX, minY = this.minY, maxX = this.maxX, maxY = this.maxY;

        if (x < 0)
            minX += x;
        if (x > 0)
            maxX += x;
        if (y < 0)
            minY += y;
        if (y > 0)
            maxY += y;
        return new AABB(minX, minY, maxX, maxY);
    }

    public boolean intersects(AABB bb) {
        if (bb.maxX < minX || bb.maxY < minY)
            return false;
        if (bb.minX > maxX || bb.minY > maxY)
            return false;
        return true;
    }

    public double solveX(double x, AABB bb) {
        if (bb.maxY <= this.minY || bb.minY >= this.maxY) {
            return x;
        }
        if (x > 0 && bb.maxX <= this.minX) {
            double amount = this.minX - bb.maxX;
            if (amount < x) {
                x = amount;
            }
        }
        if (x < 0 && bb.minX >= this.maxX) {
            double amount = this.maxX - bb.minX;
            if (amount > x) {
                x = amount;
            }
        }
        return x;
    }

    public double solveY(double y, AABB bb) {
        if (bb.maxX <= this.minX || bb.minX >= this.maxX) {
            return y;
        }
        if (y > 0 && bb.maxY <= this.minY) {
            double amount = this.minY - bb.maxY;
            if (amount < y) {
                y = amount;
            }
        }
        if (y < 0 && bb.minY >= this.maxY) {
            double amount = this.maxY - bb.minY;
            if (amount > y) {
                y = amount;
            }
        }
        return y;
    }
    
    public double distanceX(AABB bb) {
        // Rechts
        if (bb.maxX < minX) {
            return minX - bb.maxX;
        }
        // Links
        if (bb.minX > maxX) {
            return bb.minX - maxX;
        }
        return 0;
    }
    
    public double distanceY(AABB bb) {
        // Recht
        if (bb.maxY < minY) {
            return minY - bb.maxY;
        }
        // Left
        if (bb.minY > maxY) {
            return bb.minY - maxY;
        }
        return 0;
    }
    
}
