import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Publisher {
    private Map<String, List<Subscriber>> subscribersByCategory = new HashMap<>();

    public void subscribe(String category, Subscriber subscriber) {
        subscribersByCategory.computeIfAbsent(category, k -> new ArrayList<>()).add(subscriber);
    }

    public void unsubscribe(String category, Subscriber subscriber) {
        List<Subscriber> subscribers = subscribersByCategory.get(category);
        if (subscribers != null) {
            subscribers.remove(subscriber);
        }
    }

    public void notifySubscribers(String category, String message) {
        List<Subscriber> subscribers = subscribersByCategory.get(category);
        if (subscribers != null) {
            for (Subscriber subscriber : subscribers) {
                subscriber.notify(message);
            }
        }
    }
}

interface Subscriber {
    void notify(String message);
}

class User implements Subscriber {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void notify(String message) {
        System.out.println(name + " received a notification: " + message);
    }
}

public class Main {
    public static void main(String[] args) {
        Publisher publisher = new Publisher();

        // Subscribers
        User user1 = new User("Adil");
        User user2 = new User("Mihail");
        User user3 = new User("Bekzhan");
        User user4 = new User("Kamil");
        User user5 = new User("Nurzhan");

        // Category
        publisher.subscribe("news", user5);
        publisher.subscribe("sportsLiver", user2);
        publisher.subscribe("weather", user1);
        publisher.subscribe("videogames", user4);
        publisher.subscribe("sportsCity", user3);
        publisher.subscribe("weather", user4);

        // Nitifications
        publisher.notifySubscribers("news", "Breaking News: Navalnyi is dead!");
        publisher.notifySubscribers("sportsLiver", "Game update: Liverpool FC won!");
        publisher.notifySubscribers("sportsCity", "Game update: Manchester City lose!");
        publisher.notifySubscribers("weather", "Weather forecast: Today is sunny");
        publisher.notifySubscribers("videogames", "Steam: Buy Skyrim with 50% discount");

        // Uns
        publisher.unsubscribe("weather", user1);

        // Dop notification
        publisher.notifySubscribers("weather", "Weather forecast: Tomorrow is going to be rainy!");
    }
}
