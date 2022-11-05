insert into inventory(id,user_id)
SELECT 11, 777
    WHERE
    NOT EXISTS (
            SELECT id FROM inventory WHERE id = 11
        );

insert into inventory(id,user_id)
SELECT 2, 778
    WHERE
    NOT EXISTS (
            SELECT id FROM inventory WHERE id = 2
        );
insert into inventory(id,user_id)
SELECT 3, 779
    WHERE
    NOT EXISTS (
            SELECT id FROM inventory WHERE id = 3
        );

insert into inventory_info(id, created, hobby_id, serial_id, user_inventory_id)
SELECT 123,'2022-11-02',123,'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11',11
    WHERE
    NOT EXISTS (
            SELECT id FROM inventory_info WHERE id = 123
        );

insert into inventory_info(id, created, hobby_id, serial_id, user_inventory_id)
SELECT 776,'2022-11-02',123,'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11',2
    WHERE
    NOT EXISTS (
            SELECT id FROM inventory_info WHERE id = 776
        );

insert into inventory_info(id, created, hobby_id, serial_id, user_inventory_id)
SELECT 771,'2022-11-02',123,'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12',3
    WHERE
    NOT EXISTS (
            SELECT id FROM inventory_info WHERE id = 771
        );

