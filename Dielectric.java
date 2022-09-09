public class Dielectric implements Material{
    double ir;  //Index of Refraction
    //double refraction_ratio;

    public Dielectric(double index_of_reflection){
        ir = index_of_reflection;
    }

    @Override
    public boolean scatter(Ray r_in, Hit_record rec, Vec3 attenuation, Ray scattered) {
        attenuation.set(new Vec3(1.0, 1.0, 1.0));
        double refraction_ratio;
        if(rec.front_face) {
            refraction_ratio = 1.0 / ir;
        }
        else{
            refraction_ratio = ir;

        }

        Vec3 unit_direction = Vec3.unit_vector(r_in.direction());
        Vec3 refracted = Vec3.refract(unit_direction,rec.normal,refraction_ratio);
        scattered.origin = rec.p;
        scattered.direction = refracted;
        return true;

    }
}
