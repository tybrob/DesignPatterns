import java.lang.reflect.*;
//java dynamic proxy

public class ProxyDesignPattern {
    //provides a surrogate or placeholder for another object to control access to it.

    public void run() {
        Person joe = new PersonImpl("Joe");
        Person ownerProxy = getOwnerProxy(joe);
        System.out.println("Name is: " + ownerProxy.getName());
        ownerProxy.setInterests("kayaking");
        try{
            ownerProxy.setRating(10);
        }catch (Exception e) {
            System.out.println("Can't set rating from owner proxy " +e.getMessage());
        }

        Person nonOwnerProxy = getNonOwnerProxy(joe);
        System.out.println("Interests are: " + nonOwnerProxy.getInterests());
        try{
            nonOwnerProxy.setInterests("Tichu!");
        }catch (Exception e) {
            System.out.println("Can't set interests from non-owner proxy " +e.getMessage());
        }
        nonOwnerProxy.setRating(10);
        System.out.println("Rating is: " + nonOwnerProxy.getRating());
    }

    //methods to retrieve the proxy object
    private Person getOwnerProxy (Person person) {
        return (Person) Proxy.newProxyInstance(
                                person.getClass().getClassLoader(),
                                person.getClass().getInterfaces(),
                                new OwnerInvocationHandler(person)
        );
    }
    private Person getNonOwnerProxy(Person person) {
        return (Person) Proxy.newProxyInstance(
                person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new NonOwnerInvocationHandler(person));
    }

    public interface Person {
        String getName();
        void setName(String name);
        String getGender();
        void setGender(String gender);
        String getInterests();
        void setInterests(String interests);
        int getRating();
        void setRating(int rating);
        int getRatingCount();
        void setRatingCount(int ratingCount);
    }


    public class PersonImpl implements Person {
        String name;
        String gender;
        String interests;
        int rating;
        int ratingCount;

        public PersonImpl(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getInterests() {
            return interests;
        }

        public void setInterests(String interests) {
            this.interests = interests;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public int getRatingCount() {
            return ratingCount;
        }

        public void setRatingCount(int ratingCount) {
            this.ratingCount = ratingCount;
        }
    }

    public class OwnerInvocationHandler implements InvocationHandler {
        Person person;

        public OwnerInvocationHandler (Person person) {
            this.person = person;
        }

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws IllegalAccessException {
            try {
                if (method.getName().equals("setRating")) {
                    throw new IllegalAccessException();
                } else if (method.getName().startsWith("get") || method.getName().startsWith("set")) {
                    return method.invoke(person,objects);
                }
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class NonOwnerInvocationHandler implements InvocationHandler {
        Person person;

        public NonOwnerInvocationHandler(Person person) {
            this.person = person;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
            try {
                if (method.getName().startsWith("get") || method.getName().equals("setRating")) {
                    return method.invoke(person, args);
                } else if (method.getName().startsWith("set")) {
                    throw new IllegalAccessException();
                }
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
