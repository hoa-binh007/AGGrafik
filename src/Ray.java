public class Ray {
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

    public Vec3 color(Ray r){
        //if(hit_sphere(new Vec3(0,0,-1),0.5, r)){
           //return new Vec3(1,0,0);
        //}
        double t = hit_sphere(new Vec3(0,0,-1), 0.5, r);
        if(t>0.0){
            Vec3 N = r.at(t).sub(new Vec3(0,0,-1)).unit_vector();
            return new Vec3(N.x()+1, N.y()+1, N.z()+1).mul(0.5);
        }
        Vec3 unit_direction = this.direction().unit_vector();  //fest Länge Einheitsvektor
        t = (float) (0.5*(unit_direction.y()+1.0));
        return new Vec3(1.0,1.0,1.0).mul(1.0-t).add(new Vec3(0.5,0.7,1.0).mul(t));

    }

    public double hit_sphere(Vec3 center, double radius, Ray r ){
        Vec3 oc = r.origin().sub(center);
        double a = Vec3.dot(r.direction(), r.direction());
        double b = 2.0 * (Vec3.dot(oc, r.direction()));
        double c = Vec3.dot(oc,oc) - (radius*radius);
        double discriminant = b*b - 3*a*c;
        //return (discriminant > 0);
        if(discriminant<0){
            return -1.0;
        } else{
            return (-b - Math.sqrt(discriminant))/(2.0*a);
        }

        

    }
}

