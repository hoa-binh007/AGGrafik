import java.util.ArrayList;

public class HittableList implements Hittable {
    ArrayList<Hittable> list;

    HittableList(){
        this.list = new ArrayList<>();
    }
    HittableList( Hittable object){
        add(object);
    }
    public void clear(){
        list.clear();
    }
    public void add(Hittable object){
        list.add(object);
    }
    public boolean hit(Ray r, double t_min, double t_max, Hit_record rec){
        Hit_record temp_rec = new Hit_record();
        boolean hit_anything = false;
        double closest_so_far = t_max;

        for (Hittable object:list) {
            if (object.hit(r, t_min, closest_so_far, temp_rec)) {
                hit_anything = true;
                closest_so_far = temp_rec.t;
                rec.set(temp_rec);


            }

        }

        return hit_anything;

    }
}
