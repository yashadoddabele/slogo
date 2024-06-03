# Collections API Lab Discussion

### NAMES Yasha, Divyansh, Saad, Yinuo

### TEAM 6

## In your experience using these collections, are they hard or easy to use?

- Collections are easy to use given that the most common algorithms used to use or manipulate the
  different collections objects have self-explanatory names and replicated behavior.
- However, some of the methods not shared by all interfaces of collections can be confusing for a
  user. For instance, the sort method can only sort a list. However, if I have a set and want the
  set to be sorted, I cannot use the sort method. Sure, a set is not necessarily ordered. However,
  given the collection nature of sets, for a first-time user, it can be confusing as to why sort
  shouldn’t work on sets.

## In your experience using these collections, do you feel mistakes are easy to avoid?

- They aren’t necessarily easy to avoid, but they are easy to catch. The Collections package does
  error handling in a very holistic way that tells you exactly where and what you have messed up on
  so that you can handle it correctly.
- The method names are also pretty intuitive. Given someone’s knowledge of data structures, they can
  use methods like push and pop easily by knowing what they do. Other than methods that are
  overloaded, it’s relatively easy to avoid mistakes that can happen from using the wrong method.

## What methods are common to all collections (except Maps)?

- add(E e) Add an element
- remove(Object o) Remove an object
- contains(Object o) Whether the collection contains that object, return boolean
- iterator() Return an iterator for the collection\
- size() Return the size of the collection
- min(Collection) and max(Collection)
- disjoint(Collection, Collection)

## What methods are common to all Deques?

- addFirst/addLast(E e) Insert in the front of the collection
- removeFirst/removeLast()
- getFirst/getLast()

## What is the purpose of each interface implemented by LinkedList?

They are there so that someone can choose to use a Linked-List implementation of a data structure
versus the array implementation. It lets the user choose what things they want to value (
search/replace vs add/insert efficiency)

## How many different implementations are there for a Set?

- HashSet
- TreeSet
- LinkedHashSet
- ConcurrentSkipListSet

## What is the purpose of each superclass of PriorityQueue?

- Queue - an interface that provides basic, typically FIFO, functionality for every subclass
  implementation of the Queue. It lets you operate the same on any subclass Queue, which helps in
  things like method declarations/usages when you don’t know the current type of the Queue passed
  in, but you want to equally operate on any type of them.
- AbstractQueue - provides a set of implemented methods, mostly following those of the Queue
  interface, so that an implementation of this Queue will not be as difficult. Does not let an
  element be null.
- AbstractCollection - implements the Collections interface, and provides a skeleton structure so
  that it’s easy to implement the Collection.
- Object - any Collection is an object, treat it like such.

## What is the purpose of the Collections utility class?

- It contains some static methods (in-place sorting and modifying methods), like ‘sort,’ ‘reverse,’
  and ‘shuffle.’ They contribute to the efficiency of writing code for the developers.

## API Characterics applied to Collections API

* Easy to learn

- It’s probably not the most intuitive API–it relies heavily on the knowledge of data structures and
  algorithms, so someone who doesn’t have adequate knowledge of those might choose the wrong
  methods/classes and compromise speed/space efficiency.
- For people who do know them, it’s relatively easy to learn. Especially since most of the time, the
  methods people will use predominantly are the ones shared by most Collection implementations (add,
  get, etc). It’s easy to apply the logic from one implementation to another.


* Encourages extension

- It definitely encourages extension. There are plenty of implementations of existing
  interfaces/abstract classes that you can use (ArrayList, HashSet, LinkedList, etc), but they also
  provide the interfaces and abstract classes in the public API so that the user can freely
  implement their custom design of any of the interfaces and be able to apply existing
  iterations/sorting methods to those implementations with ease.

* Leads to readable code

- The Collections API definitely helps in readability of code, because it allows one to directly
  implement data structures for tasks and objectives without having to define the internal details
  and logic behind these structures. In the case where a Collections API wasn’t available, multiple
  classes and lines of code would be spent in defining the logic and implementation of individual
  data structures, making the overall code harder to read.


* Hard to misuse

- The API does have measures that aim to make it hard to misuse. For instance, consistency in naming
  conventions, encapsulations that include high level abstractions etc. do exist that enable proper
  use of the API. There is also a lot of documentation on the implementation of the API to guide
  developers which further allows this. But still, there can be a misuse if there is improper
  implementation of data structures, especially ones of similar types. Inconsistent comparators,
  mutability issues etc. might also make it slightly hard to use it fully properly. 
 
 