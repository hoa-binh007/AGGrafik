public class Sphere implements Hittable{
    public Vec3 center;
    public double  radius;

    public Sphere(){
    }
    public Sphere(Vec3 cen, double r) {
        center = cen;
        radius = r;
    }

    @Override
    public boolean hit(Ray r, double t_min, double t_max, Hit_record rec) {
        Vec3 oc = r.origin().sub(center);
        double a = r.direction().squared_length();
        double half_b = Vec3.dot(oc,r.direction());
        double c = oc.squared_length() - radius*radius;
        double discriminant = half_b * half_b - a*c;
        if(discriminant<0) {
            return false;
        }
        double sqrtd = Math.sqrt(discriminant);
        //find the  nearest root that lies in the acceptable range
        double root = (-half_b-sqrtd)/a;
        if(root < t_min || t_max < root) {
            return false;
            }
        rec.t  = root;
        rec.p = r.at(rec.t);
        //rec.normal = (rec.p.sub(center)).div(radius);
        Vec3 outward_normal = (rec.p.sub(center)).div(radius);
        rec.set_face_normal(r,outward_normal);
        return true;
    }

}

