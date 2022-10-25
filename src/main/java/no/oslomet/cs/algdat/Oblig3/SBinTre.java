package no.oslomet.cs.algdat.Oblig3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.StringJoiner;

public class SBinTre<T> {
    private static final class Node<T> // en indre nodeklasse
    {
        private T verdi; // nodens verdi
        private Node<T> venstre, høyre; // venstre og høyre barn
        private Node<T> forelder; // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder) // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot; // peker til rotnoden
    private int antall; // antall noder
    private int endringer; // antall endringer

    private final Comparator<? super T> comp; // komparator

    public SBinTre(Comparator<? super T> c) // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null)
            return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0)
                p = p.venstre;
            else if (cmp > 0)
                p = p.høyre;
            else
                return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom())
            return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot;
        Node<T> q = null;
        int cmp = 0;

        while (p != null) {
            q = p;
            cmp = comp.compare(verdi, p.verdi);
            p = cmp < 0 ? p.venstre : p.høyre;
        }

        p = new Node<>(verdi, q);

        if (q == null) {
            rot = p;
        } else if (cmp < 0) {
            q.venstre = p;
        } else {
            q.høyre = p;
        }

        antall++; // én verdi mer i treet
        return true; // vellykket innlegging
    }

    public boolean fjern(T verdi) {
        if (verdi == null) {
            return false;
        }

        Node<T> p = rot;
        Node<T> q = null;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) {
                q = p;
                p = p.venstre;
            } else if (cmp > 0) {
                q = p;
                p = p.høyre;
            } else {
                break;
            }
        }

        if (p == null) {
            return false;
        }

        if (p.venstre == null || p.høyre == null) {
            Node<T> b = p.venstre != null ? p.venstre : p.høyre;
            if (p == rot) {
                rot = b;
                if (b != null) {
                    b.forelder = null;
                }
            } else if (p == q.venstre) {
                q.venstre = b;
                if (b != null) {
                    b.forelder = q;
                }
            } else {
                q.høyre = b;
                if (b != null) {
                    b.forelder = q;
                }
            }

        } else {
            Node<T> s = p, r = p.høyre;
            while (r.venstre != null) {
                s = r;
                r = r.venstre;
            }

            p.verdi = r.verdi;

            if (s != p) {
                s.venstre = r.høyre;
            } else {
                s.høyre = r.høyre;
            }

        }

        antall--;
        return true;
    }

    public int fjernAlle(T verdi) {
        int occurrence = antall(verdi);
        int counter = 0;

        for (int i = 0; i < occurrence; i++) {
            fjern(verdi);
            counter++;
        }

        occurrence = occurrence - counter;
        return counter;
    }

    public int antall(T verdi) {
        Objects.requireNonNull(verdi, "Verdien kan ikke være NULL!");
        Node<T> p = rot;
        int forekomst = 0;
        int cmp;

        while (p != null) {
            cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) {
                p = p.venstre;
            } else {
                if (cmp == 0) {
                    forekomst++;
                }
                p = p.høyre;
            }
        }

        return forekomst;

    }

    public void nullstill() {
        for (T remove : serialize()) {
            fjernAlle(remove);
        }
        antall = 0;
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {
        Objects.requireNonNull(p, "P kan ikke være null");

        while (true) {
            if (p.venstre != null) {
                p = p.venstre;
            } else if (p.høyre != null) {
                p = p.høyre;
            } else {
                return p;
            }
        }
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        Objects.requireNonNull(p, "P kan ikke være null");
        Node<T> q = p.forelder;

        if (q == null) {
            return null;
        }

        if (q.høyre == p) {
            return q;
        } else if (q.høyre != null) {
            return førstePostorden(q.høyre);
        } else {
            return q;
        }
    }

    public void postorden(Oppgave<? super T> oppgave) {
        Node<T> firstNode = førstePostorden(rot);
        while (firstNode != null) {
            oppgave.utførOppgave(firstNode.verdi);
            firstNode = nestePostorden(firstNode);
        }
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        if (p.venstre != null) {
            postordenRecursive(p.venstre, oppgave);
        }
        if (p.høyre != null) {
            postordenRecursive(p.høyre, oppgave);
        }
        oppgave.utførOppgave(p.verdi);
    }

    public ArrayList<T> serialize() {
        ArrayList<T> returnArray = new ArrayList<>();

        Deque<Node> queue = new LinkedList<>();

        if (rot != null) {
            queue.add(rot);
        }

        while (!queue.isEmpty()) {
            Node<T> temp = queue.poll();

            returnArray.add(temp.verdi);

            if (temp.venstre != null) {
                queue.add(temp.venstre);
            }

            if (temp.høyre != null) {
                queue.add(temp.høyre);
            }
        }
        return returnArray;
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        SBinTre<K> tree = new SBinTre<>(c);

        for (K k : data) {
            tree.leggInn(k);
        }

        return tree;
    }

} // ObligSBinTre
