public class firstImage {

    public static void main(String[] args) {

        //Image
        double aspect_ratio = 16.0 / 9.0;
        int image_width = 400;
        int image_height =(int) (image_width / aspect_ratio);
        int samples_per_pixel = 100;
        int max_depth = 50;

        //World
        double R = Math.cos(Math.PI/4);
        HittableList world = new HittableList();

        //Lambertian material_left = new Lambertian(new Vec3(0,0,1));
        //Lambertian material_right = new Lambertian(new Vec3(1,0,0));

        Lambertian material_ground = new Lambertian(new Vec3(0.8,0.8,0.0));
        Lambertian material_center = new Lambertian(new Vec3(0.1,0.2,0.5));
        Dielectric material_left = new Dielectric(1.5);
        Metal material_right = new Metal(new Vec3(0.8,0.6,0.2),0.0);


        //Dielectric material_center = new Dielectric(1.5);
        //Lambertian material_center = new Lambertian(new Vec3(0.7,0.3,0.3));
        //Metal material_left = new Metal(new Vec3(0.8,0.8,0.8),0.3);
        //Dielectric material_left = new Dielectric(1.5);
        //Metal material_right = new Metal(new Vec3(0.8,0.6,0.2),0.0);

        //world.add(new Sphere(new Vec3(0.0,-100.5,-1.0), 100.0, material_ground));
        //world.add(new Sphere(new Vec3(0.0,0.0,-1.0), 0.5, material_center));
        //world.add(new Sphere(new Vec3(-1.0,0.0,-1.0), 0.5, material_left));

        world.add(new Sphere(new Vec3(0.0,-100.5,-1.0), 100.0, material_ground));
        world.add(new Sphere(new Vec3(0.0,0.0,-1.0), 0.5, material_center));
        world.add(new Sphere(new Vec3(-1.0,0.0,-1.0), 0.5, material_left));
        world.add(new Sphere(new Vec3(-R,0.0,-1.0), 0.05, material_left));
        world.add(new Sphere(new Vec3(R,0.0,-1.0), 0.5, material_right));

        //Camera
       //Camera cam = new Camera(90.0,aspect_ratio);
        //Camera cam = new Camera(new Vec3(-2,2,1),new Vec3(0,0,-1),new Vec3(0,1,0),90,aspect_ratio);
        Vec3 lookfrom = new Vec3(3,3,2);
        Vec3 lookat = new Vec3(0,0,-1);
        Vec3 vup = new Vec3(0,1,0);
        double dist_to_focus = (lookfrom.sub(lookat).length());
        double aperture = 2.0;
        Camera cam = new Camera(lookfrom,lookat,vup,20,aspect_ratio,aperture,dist_to_focus);
        //Camera cam = new Camera(new Vec3(-2,2,1),new Vec3(0,0,-1),new Vec3(0,1,0),20,aspect_ratio);

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
