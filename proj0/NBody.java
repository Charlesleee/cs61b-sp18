public class NBody {
    public static double readRadius(String s) {
        In in = new In(s);
        in.readInt();
        return in.readDouble();
    }

    public static Body[] readBodies(String s) {
        In in = new In(s);
        int num = in.readInt();
        in.readDouble();

        Body[] bodies = new Body[num];
        
        for (int i = 0; i < num; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return bodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        double time = 0;
        String fileName = args[2];
        double radius = NBody.readRadius(fileName);
        Body[] bodies = NBody.readBodies(fileName);

        StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
        StdDraw.clear();

        for (; time < T; time += dt) {
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];
            int i = 0;
            for (Body body : bodies) {
                xForces[i] = body.calcNetForceExertedByX(bodies);
                yForces[i] = body.calcNetForceExertedByY(bodies);
                i++;
            }

            for (int j = 0; j < bodies.length; j++) {
                bodies[j].update(dt, xForces[j], yForces[j]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (Body body : bodies) {
                StdDraw.picture(body.xxPos, body.yyPos, "images/" + body.imgFileName);
            }
            
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                        bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
        }
    }
}
