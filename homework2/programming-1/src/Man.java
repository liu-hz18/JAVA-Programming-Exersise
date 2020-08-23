
class Man extends BasePerson implements Person {
    private final String name;
    private final String description;
    protected int count;

    Man (final String name, final String description) {
        this.name = name;
        this.description = description;
        this.count = 0;
    }

    protected void move() {
        System.out.println("I'm moving...");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public int changeSomething() {
        this.count -= 1;
        return this.count;
    }
}

class SuperMan extends Man{
    SuperMan(final String name, final String description) {
        super(name, description);
    }

    SuperMan() {
        this("superMan", "I can fly.");
    }

    void fly() {
        System.out.println("Fly! SuperMan!");
    }

    @Override
    protected void move() {
        System.out.println("I'm flying...");
    }

    @Override
    public int changeSomething() {
        this.count += 1;
        return this.count;
    }
}
