public class Camera {
    // multiple rays per pixel
    private Vec3 origin;    //point3 = 3D point
    private Vec3 lower_left_corner;   //point3   = 3D point
    private Vec3 horizontal;       //vec3
    private Vec3 vertikal;          //vec3
    private double lens_radius;
    private Vec3 u,v,w;



    public Camera(Vec3 lookfrom, Vec3 lookat, Vec3 vup, double vfou, double aspect_ratio,double aperture, double focus_dist){
        double theta = degrees_to_radians(vfou);
        double h = Math.tan(theta/2);
        double viewport_height = 2.0*h;
        double viewport_width = aspect_ratio * viewport_height;
        //double focal_length = 1.0;
        w = Vec3.unit_vector(lookfrom.sub(lookat));
        u = Vec3.unit_vector(Vec3.cross(vup,w));
        v = Vec3.cross(w,u);
        
        origin = lookfrom;
        horizontal = u.mul(viewport_width*focus_dist);
        vertikal = v.mul(viewport_height*focus_dist);
        lower_left_corner = origin.sub((horizontal).div(2)).sub((vertikal).div(2)).sub(w.mul(focus_dist));
        
        lens_radius = aperture/2;

        /*
        horizontal = u.mul(viewport_width);
        vertikal = v.mul(viewport_height);
        lower_left_corner = origin.sub((horizontal).div(2)).sub((vertikal).div(2)).sub(w);
        
         */
/*
        origin = new Vec3(0,0,0);
        horizontal = new Vec3(viewport_width, 0.0,0.0);
        vertikal = new Vec3(0.0,viewport_height,0.0);
        lower_left_corner = origin.sub((horizontal).div(2)).sub((vertikal).div(2)).sub(new Vec3 (0,0,focal_length));

 */
    }
    public static double degrees_to_radians(double degrees){
        return degrees * Math.PI /180.0;
    }

    Ray get_ray(double s, double t){
        Vec3 rd = Vec3.random_in_unit_disk().mul(lens_radius);
        Vec3 offset = u.mul(rd.x()).add(v.mul(rd.y()));
        
        return new Ray(origin.add(offset), lower_left_corner.add(horizontal.mul(s)).add(vertikal.mul(t)).sub(origin).sub(offset));
    }
    /*
    Ray get_ray(double u, double v){
        //System.out.println(horizontal);
        return new Ray(origin, lower_left_corner.add(horizontal.mul(u)).add(vertikal.mul(v)).sub(origin));
    }

     */


}
