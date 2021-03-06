import java.util.HashSet;
import java.util.Set;

public class Group implements  ITestable {
    private int groupId;
    HashSet<Hotel> hotels;

    public Group(int id) {
        hotels = new HashSet<Hotel>();
        groupId = id;
    }


    public void addHotelToGroup(Hotel hotel) {
        hotels.add(hotel);
    }

    //getters

    public int getGroupId() {
        return groupId;
    }

    public HashSet<Hotel> getHotels() {
        return hotels;
    }

    public boolean constraint_1(){
        for (Hotel h1:this.getHotels()){
            for (Hotel h2:this.getHotels()) {
                if (h1 != h2 && h1.getCity().equals(h2.getCity())){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean checkConstraints() {
        return constraint_1() && constraint_4();
    }

    public static boolean checkAllIntancesConstraints(Model model) {
        for (Object o : model.allObjects) {
            if (o instanceof Group) {
                if (!((Group) o).checkConstraints()) return false;
            }
        }
        return true;
    }

    /**
     * Maximum one hotel of the same group in a city
     *
     * @return
     */
    public boolean constraint_1() {
        for (Hotel h1 : this.getHotels()) {
            for (Hotel h2 : this.getHotels()) {
                if (h1 != h2 && h1.getCity().equals(h2.getCity())) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * same services to all the hotels in the same group
     *
     * @return
     */
    public boolean constraint_4() {
        HashSet<Hotel> list_of_hotels = this.hotels;
        Set<Service> setServices = new HashSet<>();
        for (Hotel h1 : list_of_hotels) {
            for (Service s1 : h1.getServices().keySet()) {
                setServices.add(s1);
            }
        }
        for (Hotel h2 : list_of_hotels) {
            for (Service s2 : setServices) {
                if (!h2.getServices().keySet().contains(s2)) {
                    return false;
                }
            }
        }
        return true;
    }



}
