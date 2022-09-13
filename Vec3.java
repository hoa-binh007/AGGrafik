import java.lang.Math;
import static java.lang.Math.min;


public class Vec3 {
    private double[] e = new double[3];

    public Vec3  (){

    }

    public Vec3(double e0, double e1, double e2) {
        e[0] = e0;
        e[1] = e1;
        e[2] = e2;
    }

    public double x() {
        return e[0];
    }

    public double y() {
        return e[1];
    }

    public double z() {
        return e[2];
    }

    public double r() {
        return e[0];
    }

    public double g() {
        return e[1];
    }

    public double b() {
        return e[2];
    }

    public double length() {
        return Math.sqrt(e[0] * e[0] + e[1] * e[1] + e[2] * e[2]);
    }

    public double squared_length() {
        return e[0] * e[0] + e[1] * e[1] + e[2] * e[2];
    }

    // vec3 >Utility Functions
    public Vec3 minus() {
        return new Vec3(-e[0], -e[1], -e[2]);
    }

    public Vec3 add(Vec3 other) {
        return new Vec3(e[0] + other.e[0], e[1] + other.e[1], e[2] + other.e[2]);
    }

    public Vec3 sub(Vec3 other) {
        return new Vec3(e[0] - other.e[0], e[1] - other.e[1], e[2] - other.e[2]);
    }

    public Vec3 mul(Vec3 other) {
        return new Vec3(e[0] * other.e[0], e[1] * other.e[1], e[2] * other.e[2]);
    }

    public Vec3 mul(double t) {
        return new Vec3(t * e[0], t * e[1], t * e[2]);
    }

    public Vec3 div(Vec3 other) {
        return new Vec3(e[0] / other.e[0], e[1] / other.e[1], e[2] / other.e[2]);
    }

    public Vec3 div(double t) {
        return new Vec3(e[0] / t, e[1] / t, e[2] / t);
    }

    public static double dot(Vec3 v1, Vec3 v2) {
        return v1.e[0] * v2.e[0] + v1.e[1] * v2.e[1] + v1.e[2] * v2.e[2];
    }

    public static Vec3 cross(Vec3 v1, Vec3 v2) {
        return new Vec3((v1.e[1] * v2.e[2] - v1.e[2] * v2.e[1]), (-(v1.e[0] * v2.e[2] - v1.e[2] * v2.e[0])
        ), (v1.e[0] * v2.e[1] - v1.e[1] * v2.e[0]));
    }

    public void unit() {
        double k = 1.0 / length();
        e[0] *= k;
        e[1] *= k;
        e[2] *= k;
    }

    public Vec3 unit_vector() {

        return this.div(this.length());
    }

    public static Vec3 unit_vector(Vec3 v) {

        return v.div(v.length());
    }



    public void set(Vec3 v) {
        e[0] = v.e[0];
        e[1] = v.e[1];
        e[2] = v.e[2];
    }

    public double get(int i) {
        return e[i];
    }

    public String toString() {
        return "<" + e[0] + "," + e[1] + "," + e[2] + ">";
    }

    public boolean equals(Vec3 v2) {
        return e[0] == v2.e[0] && e[1] == v2.e[1] && e[2] == v2.e[2];
    }

    public double clamp(double x, double min, double max) {
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    public void write_color(Vec3 pixel_color, int samples_per_pixel) {

        //int ir = (int) (pixel_color.r());
        //int ig = (int) (pixel_color.g());
        //int ib = (int) (pixel_color.b());
        /*int ir = (int) (pixel_color.x());
        int ig = (int) (pixel_color.g());
        int ib = (int) (pixel_color.b());
         */
        // Divide the color by the number of samples
        double scale = 1.0 / samples_per_pixel;
        //ir *= scale;
        //ig *= scale;
        //ib *= scale;

        /* ir = (int) Math.sqrt(scale*ir);
        ig = (int) Math.sqrt(scale*ig);
        ib = (int) Math.sqrt(scale*ib);

        ir = (int) (256.0*clamp(ir,0.0, 0.999));
        ig = (int) (256.0*clamp(ig,0.0, 0.999));
        ib = (int) (256.0*clamp(ib,0.0, 0.999));
        */



        int ir = (int) (256.0*clamp(pixel_color.r() * scale,0.0, 0.999));
        int ig = (int) (256.0*clamp(pixel_color.g() * scale,0.0, 0.999));
        int ib = (int) (256.0*clamp(pixel_color.b() * scale,0.0, 0.999));

        //Write the translated [0,255] value of each color component.
        //System.out.println(256 * clamp(ir, 0.0, 0.999));
        //System.out.println(" " + 256 * clamp(ig, 0.0, 0.999));
        //System.out.println(" " + (256 * clamp(ib, 0.0, 0.999)) + " " + "\n");
        System.out.println(ir + " " + ig + " " + ib);
    }

    public static double random_double() {
        //Return a random real in [0,1]
        return Math.random();
    }

    public static double random_double(double min, double max) {
        //Return a random real in [min, max]
        return min + (max - min) * Math.random();
    }


    //ZU BEARBEITEN
    public static Vec3 random(double min, double max) {
        return new Vec3(Math.random(), Math.random(), Math.random());
    }

    public static Vec3 random_in_unit_sphere() {
        while (true) {
            Vec3 p = Vec3.random(-1, 1);
            if (p.squared_length() >= 1) {
                continue;
            } else {
                return p;
            }

        }

    }
    public static Vec3 random_unit_vector(){
        return unit_vector(random_in_unit_sphere());

    }

    public static Vec3 random_in_hemisphere(Vec3 normal){
        Vec3 in_unit_sphere = random_in_unit_sphere();
            if(dot(in_unit_sphere, normal)>0.0){ // In the same hemisphere as the normal
                return in_unit_sphere;
            }
            else{
                return in_unit_sphere.mul(-1);
            }
    }

    //Generate random point inside unit disk
    public static Vec3 random_in_unit_disk(){
        while (true){
            Vec3 p = new Vec3(random_double(-1,1), random_double(-1,1), 0);
            if(p.squared_length()>=1){
                continue;
            }
            else {
                return p;
            }
        }
    }


    public boolean near_zero(){
        //Return true if the vector is close to zero in all dimensions
        float s = -1e8F;
        return (Math.abs(e[0]) < s)&& (Math.abs(e[1]) < s) && (Math.abs(e[2])<s);
    }

    public static Vec3 refract(Vec3 uv, Vec3 n, double etai_over_etat){
       double cos_theta = Math.min(dot((uv.mul(-1)),n),1.0);
       Vec3 r_out_perp = (uv.add(n.mul(cos_theta))).mul(etai_over_etat);
       Vec3 r_out_parallel = n.mul(-1*Math.sqrt(Math.abs(1.0-(r_out_perp.squared_length()))));
       return r_out_perp.add(r_out_parallel);
    }


}






