public class Hit_record {
    public Vec3 p; //Trefferpunkt wo man trifft
    public Vec3 normal; // steht senkrecht auf der Kugel
    public double t; //wie weit geht man den Ray entlangt
    boolean front_face;


    public void set_face_normal(Ray r, Vec3 outward_normal){
        front_face = Vec3.dot(r.direction(),outward_normal)<0;
        if(front_face){
            this.normal  = outward_normal;
        }
        else{
            this.normal = outward_normal.mul(-1);
        }
    }

    public void set(Hit_record temp_rec) {
        this.p = temp_rec.p;
        this.normal = temp_rec.normal;
        this.t = temp_rec.t;
        this.front_face = temp_rec.front_face;
    }
}
