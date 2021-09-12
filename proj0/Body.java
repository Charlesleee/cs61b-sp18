import java.lang.Math;

public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        double dx = xxPos - b.xxPos;
        double dy = yyPos - b.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Body b) {
        if (b.equals(this)) return 0;
        double distance = this.calcDistance(b);
        double force = 6.67e-11 * this.mass * b.mass / (distance * distance);
        return force;
    }

    public double calcForceExertedByX(Body b) {
        double force = this.calcForceExertedBy(b);
        double dx = b.xxPos - xxPos;
        double distance = this.calcDistance(b);
        return force * dx / distance;
    }

    public double calcForceExertedByY(Body b) {
        double force = this.calcForceExertedBy(b);
        double dy = b.yyPos - yyPos;
        double distance = this.calcDistance(b);
        return force * dy / distance;
    }

    public double calcNetForceExertedByX(Body[] bodies) {
        double force = 0;
        for (Body body : bodies) {
            if (this.equals(body)) continue;
            force += this.calcForceExertedByX(body);
        }
        return force;
    }

    public double calcNetForceExertedByY(Body[] bodies) {
        double force = 0;
        for (Body body : bodies) {
            if (this.equals(body)) continue;
            force += this.calcForceExertedByY(body);
        }
        return force;
    }

    public void update(double dt, double forceX, double forceY) {
        double aNetX = forceX / this.mass;
        double aNetY = forceY / this.mass;
        xxVel = xxVel + dt * aNetX;
        yyVel = yyVel + dt * aNetY;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }
}