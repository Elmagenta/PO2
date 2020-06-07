package ripasso;

public class Iteratori {
    public interface Iterator {
        public boolean hasNext();
        public Object next();
    }

    public interface Container {
        public Iterator getIterator();
    }

    public static class NameRepository implements Container {
        public String[] names = {"Robert", "John", "Julie", "Lora"};

        @Override
        public Iterator getIterator() {
            return new NameIterator();
        }

        private class NameIterator implements Iterator {
            int index;

            @Override
            public boolean hasNext() {
                return (index < names.length);
            }

            @Override
            public Object next() {

                if (this.hasNext()) {
                    return names[index++];
                }

                return null;
            }
        }
    }

    public static void main(String[] args) {
        NameRepository namesRepository = new NameRepository();
        Iterator iter = namesRepository.getIterator();

        while (iter.hasNext()){
            String name = (String)iter.next();
            System.out.println("Name : " + name);
        }
    }
}
