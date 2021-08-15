//Name: Brennan Lee
//Student No; A0217067E

public class Team implements Comparable<Team> {
    public int id;
    public int score;
    public int penalty;

    Team(int id, int score, int penalty) {
        this.id = id;
        this.score = score;
        this.penalty = penalty;
    }



    @Override
    //score,penalty, then id.
    public int compareTo(Team team2) {
        if (this.score > team2.score) {
            return 1;
        } else if (this.score < team2.score) {
            return -1;
        } else {
            if (this.penalty < team2.penalty) {
                return 1;
            } else if (this.penalty> team2.penalty) {
                return -1;
            } else {
                if (this.id < team2.id) {
                    return 1;
                } else if (this.id > team2.id){
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }

    public boolean compareId(int i) {
        return this.id == i;
    }
}
