INSERT INTO "PUBLIC"."ABOUT" VALUES
(1, 'Group1Description', 'Group1History', 'Group1Privacy', 'Group1Visibility'),
(2, 'Group2Description', 'Group2History', 'Group2Privacy', 'Group2Visibility');

INSERT INTO "PUBLIC"."GROUP_PAGE" VALUES
(1, 'Group1', 1),
(2, 'Group2', 2);

INSERT INTO "PUBLIC"."GROUP_PAGE_POST" VALUES
(1, 'Group1Post1', 1),
(2, 'Group1Post2', 1),
(3, 'Group1Post3', 1),
(4, 'Group1Post4', 1),
(5, 'Group1Post5', 1),
(6, 'Group1Post6', 1),
(7, 'Group1Post7', 1),
(8, 'Group1Post8', 1),
(9, 'Group1Post9', 1),
(10, 'Group1Post10', 1),
(11, 'Group2Post1', 2),
(12, 'Group2Post2', 2),
(13, 'Group2Post3', 2),
(14, 'Group2Post4', 2),
(15, 'Group2Post5', 2),
(16, 'Group2Post6', 2),
(17, 'Group2Post7', 2),
(18, 'Group2Post8', 2),
(19, 'Group2Post9', 2),
(20, 'Group2Post10', 2);

INSERT INTO "PUBLIC"."GROUP_PAGE_MEMBER" VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1),
(6, 6, 1),
(7, 7, 1),
(8, 8, 1),
(9, 9, 1),
(10, 10, 1),
(11, 1, 2),
(12, 2, 2),
(13, 3, 2),
(14, 4, 2),
(15, 5, 2),
(16, 6, 2),
(17, 7, 2),
(18, 8, 2),
(19, 9, 2),
(20, 10, 2);

COMMIT;