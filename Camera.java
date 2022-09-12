public class Camera {
    // multiple rays per pixel
    private Vec3 origin;    //point3 = 3D point
    private Vec3 lower_left_corner;   //point3   = 3D point
    private Vec3 horizontal;       //vec3
    private Vec3 vertikal;          //vec3

    public Camera(double vfou, double aspect_ratio){
        double theta = degrees_to_radians(vfou);
        double h = Math.tan(theta/2);
        double viewport_height = 2.0*h;
        double viewport_width = aspect_ratio * viewport_height;
        double focal_length = 1.0;

        origin = new Vec3(0,0,0);
        horizontal = new Vec3(viewport_width, 0.0,0.0);
        vertikal = new Vec3(0.0,viewport_height,0.0);
        lower_left_corner = origin.sub((horizontal).div(2)).sub((vertikal).div(2)).sub(new Vec3 (0,0,focal_length));
    }
    public static double degrees_to_radians(double degrees){
        return degrees * Math.PI /180.0;
    }
    Ray get_ray(double u, double v){
        //System.out.println(horizontal);
        return new Ray(origin, lower_left_corner.add(horizontal.mul(u)).add(vertikal.mul(v)).sub(origin));
    }


}
