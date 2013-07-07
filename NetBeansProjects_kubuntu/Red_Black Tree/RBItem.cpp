enum Color { RED, BLACK };

template < typename Key, typename Element >
class RBItem : public Item<Key, Element>
{
private:
    Color col;

public:
    RBItem( const Key& k = Key(),
            const Element& e = Element(), Color c = RED )
            : Item<Key, Element>(k,e), col(c)
    {
    }

    Color color() const
    {
        return col;
    }

    setColor( Color c )
    {
        col = c;
    }
};