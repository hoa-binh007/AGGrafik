public class Ray {
    //let's think of a ray as a function P(t)=A+t*b
    //Here P is a 3D position along a line in 3D. A is the ray origin and b is ray direction
    Vec3 origin;
    Vec3 direction;
    double t;  //Abstand bis man irgendwas trifft

    public Ray() {

    }

    public Ray(Vec3 a, Vec3 b) {
        origin = a;
        direction  = b;
        t = 0;
    }

    public Ray(Vec3 a, Vec3 b, double ti) {
        origin = a;
        direction = b;
        t = ti;
    }

    public Vec3 origin() {
        return origin;
    }

    public Vec3 direction() {
        return direction;
    }

    public Vec3 at(double t){
        return origin.add(direction.mul(t));
    }

    public double time() {
        return t;
    }

    public Vec3 point_at_parameter(double t) {
        return origin.add(direction.mul(t));
    }

    public void set(Ray r) {
        origin = r.origin;
        direction = r.direction;
        t = r.t;
    }

    //the function return the color of the background(a simple gradient)
    public Vec3 color(Ray r, Hittable world, int depth){
        //if(hit_sphere(new Vec3(0,0,-1),0.5, r)){
           //return new Vec3(1,0,0);
        //}
        Hit_record rec = new Hit_record();
        // If we're exceeded the ray bounce limit, no more light is gathered.
        if (depth <= 0){

            return new Vec3(0,0,0);
        }
        if(world.hit(r,0.001,Double.POSITIVE_INFINITY,rec)){
            Ray scattered = new Ray();
            Vec3 attenuation = new Vec3();

            if(rec.mat_ptr.scatter(r, rec, attenuation, scattered)){
                return attenuation.mul(color( scattered, world, depth -1));


            }

            //Vec3 target = rec.p.add(Vec3.random_in_hemisphere(rec.normal));
            //Vec3 target = rec.p.add(rec.normal).add(Vec3.random_unit_vector());
            //return color(new Ray(rec.p,target.sub(rec.p)), world).mul(0.5);
            //**Vec3 dir = target.sub(rec.p);
            //return  color(new Ray(rec.p,target.sub(rec.p)), world,depth-1).mul(0.5);

            //return color(new Ray(rec.p.add(dir.mul(0.01)), dir), world).mul(0.5);

            //return (rec.normal.add(new Vec3(0.5,0.7,1.0)).mul(0.5
            return new Vec3(0,0,0);
        }
        /*double t = hit_sphere(new Vec3(0,0,-1), 0.5, r);
        if(t>0.0){
            Vec3 N = r.at(t).sub(new Vec3(0,0,-1)).unit_vector();
            return new Vec3(N.x()+1, N.y()+1, N.z()+1).mul(0.5);
        }*/
        Vec3 unit_direction = this.direction().unit_vector();  //fest Länge Einheitsvektor
        t = (float) (0.5*(unit_direction.y()+1.0));
        return new Vec3 (1.0,1.0,1.0).mul(1.0-t).add(new Vec3 (0.5,0.7,1.0).mul(t));
        //return new Vec3(1.0,1.0,1.0).mul(1.0-t).add(new Vec3(0.5,0.7,1.0).mul(t));

    }

    public double hit_sphere(Vec3 center, double radius, Ray r ){
        Vec3 oc = r.origin().sub(center);
        double a = r.direction().squared_length();
        double half_b = Vec3.dot(oc, r.direction());
        double c = oc.squared_length() - (radius*radius);
        double discriminant = half_b*half_b - a*c;
        //return (discriminant > 0);
        if(discriminant<0){
            return -1.0;
        } else{
            return (-half_b - Math.sqrt(discriminant))/(a);
        }
    }
}

