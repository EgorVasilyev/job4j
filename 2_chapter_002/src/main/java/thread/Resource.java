package thread;

class Resource {
    private int i;

    public Resource(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public void changeI() {
        int i = this.i;
        i++;
        this.i = i;
    }
}
class MyThread extends Thread {
    private Resource resource;

    public MyThread(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        resource.changeI();
    }
}


