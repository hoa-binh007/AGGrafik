import java.util.Random;

public class firstImage {

    public static void main(String[] args) {

        //Image
        double aspect_ratio = 16.0 / 9.0;
        int image_width = 400;
        int image_height =(int) (image_width / aspect_ratio);
        int samples_per_pixel = 100;
        int max_depth = 50;

        //World
        HittableList world = new HittableList();
        world.add(new Sphere(new Vec3(0,0,-1), 0.5));
        world.add(new Sphere(new Vec3(0,-100.5,-1), 100));

        //Camera
        Camera cam = new Camera();

        /*double viewport_height = 2.0;
        double viewport_width = aspect_ratio * viewport_height;
        double focal_length = 1.0;

        Vec3 origin = new Vec3(0,0,0);   //wo ist der Nullpunkt
        Vec3 horizontal = new Vec3(viewport_width,0,0); //bezieht sich die Camera
        Vec3 vertical = new Vec3(0,viewport_height,0);
        Vec3 lower_left_corner = origin.sub(horizontal.div(2)).sub(vertical.div(2)).sub(new Vec3(0,0,focal_length)); //Koordinaten links unten
        */

        //Render

        System.out.print("P3" + "\n" + image_width + " " + image_height+"\n" + "255" +"\n");

        int j = image_height-1;
        for (; j>=0;j--) {
            System.err.print("\rScanlines remaining: " + j + " ");
            for (int i = 0; i < image_width; i++) {
                Vec3 pixel_color = new Vec3 (0,0,0);
                for (int s=0; s < samples_per_pixel; s++){
                    double u = (double) (i+ Math.random()) /(image_width-1);
                    double v = (double) (j+ Math.random()) /(image_height-1);
                    Ray r = cam.get_ray(u,v);
                    pixel_color = r.color(r,world, max_depth).add(pixel_color);

                }
                pixel_color.write_color(pixel_color, samples_per_pixel);



                //Vec3 pixel_color = new Vec3((double) i / (image_width - 1),(double) j / (image_height - 1),0.25);
                //pixel_color.write_color(pixel_color);

                /*double r = (double) i / (image_width - 1);
                double g = (double) j / (image_height - 1);
                double b = 0.25;

                int ir = (int) (255.999 * r);
                int ig = (int) (255.999 * g);
                int ib = (int) (255.999 * b);

                System.out.println(ir + " " + ig + " " + ib + " " + "\n");

                 */

                /*
                Type aliases for vec3
                using point3 = vec3;   // 3D point
                using color = vec3;    // RGB color


                 */
            }
        }
        //System.out.println("\nDone.\n");
          System.err.println("\nDone.\n");



    }
    //Utility Functions
    public double degrees_to_radians(double degree){
        return degree*Math.PI/180.0;
    }

}
