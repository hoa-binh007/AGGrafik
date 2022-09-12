public class Dielectric implements Material{
    double ir;  //Index of Refraction
    //double refraction_ratio;

    public Dielectric(double index_of_refraction){
        ir = index_of_refraction;
    }

    @Override
    public boolean scatter(Ray r_in, Hit_record rec, Vec3 attenuation, Ray scattered) {
        attenuation.set(new Vec3(1.0,1.0,1.0));
        double refraction_ratio;
        if(rec.front_face) {
            refraction_ratio = 1.0 / ir;
        }
        else{
            refraction_ratio = ir;
        }

        Vec3 unit_direction = Vec3.unit_vector(r_in.direction());

        double cos_theta = Math.min(Vec3.dot(unit_direction.mul(-1),rec.normal),1.0);
        double sin_theta = Math.sqrt(1.0 - cos_theta * cos_theta) ;
        boolean cannot_refract = refraction_ratio * sin_theta > 1.0;
        Vec3 direction;
        if(cannot_refract || reflectance(cos_theta, refraction_ratio) > Vec3.random_double()) {
          //if(cannot_refract) {
              direction = Lambertian.reflect(unit_direction, rec.normal);
          } else {
            direction = Vec3.refract(unit_direction, rec.normal, refraction_ratio);
            scattered.origin = rec.p;
            scattered.direction = direction;
        }
        return true;


        //Vec3 refracted = Vec3.refract(unit_direction,rec.normal,refraction_ratio);
        //scattered.origin = rec.p;
        //scattered.direction = refracted;
        //return true;


    }
    private static double reflectance(double cosine, double ref_idx){
        // Use Schlicks approximation for reflectance.
        double r0 = (1-ref_idx) / (1+ref_idx);
        r0= r0 * r0;
        return  r0 +(1-r0)*Math.pow((1-cosine),5);
    }






}
