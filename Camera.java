public class Camera {
    private Vec3 origin;
    private Vec3 lower_left_corner;
    private Vec3 horizontal;
    private Vec3 vertikal;

    public Camera(){
        double aspect_ratio = 16.0/9.0;
        double viewport_height = 2.0;
        double viewport_width = aspect_ratio * viewport_height;
        double focal_length = 1.0;


        origin = new Vec3(0,0,0);
        horizontal = new Vec3(viewport_width, 0.0,0.0);
        vertikal = new Vec3(0.0,viewport_height,0.0);
        lower_left_corner = origin.sub((horizontal).div(2)).sub((vertikal).div(2)).sub(new Vec3 (0,0,focal_length));
    }
    Ray get_ray(double u, double v){
        //System.out.println(horizontal);
        return new Ray(origin, lower_left_corner.add(horizontal.mul(u)).add(vertikal.mul(v)).sub(origin));
    }


}
