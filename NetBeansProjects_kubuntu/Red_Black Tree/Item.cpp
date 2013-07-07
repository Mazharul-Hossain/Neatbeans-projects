template < typename Key, typename Element >
class Item
{
private:
    Key _key;
    Element _elem;

public:
    Item ( const Key& k = Key(), const Element& e = Element() )
            :  _key(k), _elem(e)
    {
    }

    const Key& key() const
    {
        return _key;
    }

    Element& element() const
    {
        return _elem;
    }

    const Element& element() const
    {
        return _elem;
    }

    void setKey( const Key& k )
    {
        _key = k;
    }

    void setElement( const Element& e )
    {
        _elem = e;
    }
};