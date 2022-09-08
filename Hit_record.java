public class Hit_record {
    public Vec3 p; //Trefferpunkt wo man trifft
    public Vec3 normal; // steht senkrecht auf der Kugel
    public Material mat_ptr;
    public double t; //wie weit geht man den Ray entlangt
    boolean front_face;


    public void set_face_normal(Ray r, Vec3 outward_normal){
        front_face = Vec3.dot(r.direction(),outward_normal)<0;
        if(front_face){
            //ray is inside the sphere
            this.normal  = outward_normal;
        }
        else{
            //ray is outside the sphere
            this.normal = outward_normal.mul(-1);
        }
    }

    public void set(Hit_record temp_rec) {
        this.p = temp_rec.p;
        this.normal = temp_rec.normal;
        this.t = temp_rec.t;
        this.front_face = temp_rec.front_face;
        this.mat_ptr = temp_rec.mat_ptr;
    }

    public boolean front_face(Ray r, Vec3 outward_normal) {
        if(Vec3.dot( r.direction, outward_normal)>0.0){
            //ray is inside the sphere
            normal = outward_normal.mul(-1);
            front_face = false;
        }
        else{
            //ray is outside the sphere
            normal = outward_normal;
            front_face = true;
        }
        return front_face;
    }
}
